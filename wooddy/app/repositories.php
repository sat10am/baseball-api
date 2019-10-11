<?php
declare(strict_types=1);

use App\Domain\Baseball\BaseballRepository;
use App\Domain\User\UserRepository;
use App\Infrastructure\Persistence\Baseball\SessionBaseballRepository;
use App\Infrastructure\Persistence\User\InMemoryUserRepository;
use DI\ContainerBuilder;

return function (ContainerBuilder $containerBuilder) {
    // Here we map our UserRepository interface to its in memory implementation
    $containerBuilder->addDefinitions([
        BaseballRepository::class =>  \DI\autowire(SessionBaseballRepository::class),
        UserRepository::class => \DI\autowire(InMemoryUserRepository::class),
    ]);
};
