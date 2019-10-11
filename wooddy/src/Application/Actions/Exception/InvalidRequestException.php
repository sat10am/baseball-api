<?php
declare(strict_types=1);

namespace App\Application\Actions\Exception;

use Exception;

class InvalidRequestException extends Exception
{
    public $message = '입력값을 확인해주세요.';
}
