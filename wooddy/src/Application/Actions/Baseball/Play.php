<?php
declare(strict_types=1);

namespace App\Application\Actions\Baseball;

use App\Domain\Baseball\Baseball;
use Exception;
use App\Application\Actions\Exception\InvalidRequestException;
use phpDocumentor\Reflection\Types\Self_;
use Psr\Http\Message\ResponseInterface as Response;

class Play extends BaseballAction
{
    private const TRY_MAX_COUNT = 20;

    protected function action(): Response
    {
        try {
            $params = $this->request->getQueryParams();

            $id = $params['id'];
            $reqNumber = $params['number'];

            if (empty($id) || empty($reqNumber)) {
                throw new InvalidRequestException();
            }

            $result = $this->verify($this->getBaseball($id), (String)$reqNumber);

            return $this->respondWithData($result);

        } catch (Exception $e) {
            return $this->respondWithData($e->getMessage());
        }
    }

    private function verify(Baseball $baseball, String $reqNumber): array
    {
        $targetNumber = $baseball->getRandomNumber();
        $id = $baseball->getId();
        $tryCount = $baseball->getTryCount();
        $result = static function ($correct, $judgement) use ($reqNumber, $tryCount) {
            return [
                'CORRECT' => $correct,
                'JUDGEMENT' => $judgement,
                'TRIED_NUMBER' => $reqNumber,
                'TRY_COUNT' => $tryCount,
            ];
        };

        if ($targetNumber === $reqNumber) {
            $this->baseballRepository->deleteUser($id);
            return $result(true, '4S');
        }

        $ballCount = $strikeCount = 0;
        foreach (str_split($targetNumber) as $idx => $target) {
            if ($target === $reqNumber[$idx]) {
                $strikeCount++;
            } else {
                $reqChars = str_split($reqNumber);
                unset($reqChars[$idx]);

                if (array_search($target, $reqChars, true) > 0) {
                    $ballCount++;
                }
            }
        }

        if (self::TRY_MAX_COUNT <= $tryCount) {
            $this->baseballRepository->deleteUser($id);
        }

        return $result(false, "{$strikeCount}S {$ballCount}B");
    }

    private function getBaseball(String $id): Baseball
    {
        $baseball = $this->baseballRepository->findBaseballById($id);

        if ($baseball === null) {
            $this->baseballRepository->createUser($id);
            $baseball = $this->baseballRepository->findBaseballById($id);
        }

        return $baseball;
    }
}
