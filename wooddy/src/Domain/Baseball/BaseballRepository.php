<?php
declare(strict_types=1);

namespace App\Domain\Baseball;

use App\Domain\Baseball\Baseball;

interface BaseballRepository
{
    public function findBaseballById($id): ?Baseball;

    public function createUser($id) : void ;

    public function deleteUser($id) : void ;
}
