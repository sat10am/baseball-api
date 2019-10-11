<?php
declare(strict_types=1);

namespace App\Domain\Baseball;

use JsonSerializable;
use phpDocumentor\Reflection\Types\Integer;

class Baseball implements JsonSerializable
{

    /**
     * 식별 아이디
     * @var string
     */
    private $id;

    /**
     * 랜던 번호
     * @var string
     */
    private $randomNumber;

    /**
     * 요청 횟수
     * @var Integer
     */
    private $tryCount;

    public function __construct()
    {
    }

    public function load($data): void
    {
        foreach ($data AS $key => $value) {
            $this->{$key} = $value;
        }
    }

    public function save(string $id, string $randomNumber, int $tryCount): void
    {
        $this->id = $id;
        $this->randomNumber = $randomNumber;
        $this->tryCount = $tryCount;
    }

    public function plusTryCount(): void
    {
        ++$this->tryCount;
    }

    public function getId(): string
    {
        return (string) $this->id;
    }

    public function getRandomNumber(): string
    {
        return (string) $this->randomNumber;
    }

    public function getTryCount(): int
    {
        return (int) $this->tryCount;
    }

    public function jsonSerialize(): array
    {
        return [
            'id' => $this->id,
            'randomNumber' => $this->randomNumber,
            'tryCount' => $this->tryCount,
        ];
    }
}
