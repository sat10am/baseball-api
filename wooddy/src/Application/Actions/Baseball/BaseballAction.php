<?php
declare(strict_types=1);

namespace App\Application\Actions\Baseball;

use App\Application\Actions\Action;
use App\Domain\Baseball\BaseballRepository;
use Psr\Log\LoggerInterface;

abstract class BaseballAction extends Action
{
    protected $baseballRepository;

    public function __construct(LoggerInterface $logger, BaseballRepository $baseballRepository)
    {
        parent::__construct($logger);
        $this->baseballRepository = $baseballRepository;
    }
}
