<?php
declare(strict_types=1);

namespace App\Infrastructure\Persistence\Baseball;

use App\Domain\Baseball\Baseball;
use App\Domain\Baseball\BaseballRepository;

class SessionBaseballRepository implements BaseballRepository
{
    public const SESSION_NAME = 'baseball';

    public function __construct()
    {
        if (!isset($_SESSION[self::SESSION_NAME])) {
            $_SESSION[self::SESSION_NAME] = [];
        }
    }

    public function findBaseballById($id): ?Baseball
    {
        $sessionData = $this->getSession($id);

        if (empty($sessionData)) {
            return null;
        }

        $newBaseball = new Baseball();
        $newBaseball->load($sessionData);
        $newBaseball->plusTryCount();

        $this->setSession($newBaseball);

        return $newBaseball;
    }

    public function deleteUser($id): void
    {
        $this->removeSession($id);
    }

    public function createUser($id): void
    {
        $newBaseball = new Baseball();
        $newBaseball->save($id, $this->randomGen(), 0);

        $this->setSession($newBaseball);
    }

    private function randomGen(): string
    {
        $numbers = range(1, 9);
        shuffle($numbers);

        $result = '';
        foreach ($numbers as $idx => $number) {
            if ($idx === 4) {
                break;
            }
            $result .= $number;
        }

        return $result;
    }

    private function removeSession($id): void
    {
        unset($_SESSION[self::SESSION_NAME][$id]);
    }

    private function setSession(Baseball $data): void
    {
        if (!isset($_SESSION[self::SESSION_NAME])) {
            $_SESSION[self::SESSION_NAME] = [];
        }

        $_SESSION[self::SESSION_NAME][$data->getId()] = $data->jsonSerialize();
    }

    private function getSession(String $id): ?array
    {
        if (!isset($_SESSION[self::SESSION_NAME][$id])) {
            $_SESSION[self::SESSION_NAME][$id] = [];
            return $_SESSION[self::SESSION_NAME][$id];
        }

        return $_SESSION[self::SESSION_NAME][$id];
    }
}
