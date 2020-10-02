package com.piggybank.piggybank.repositories;

import com.piggybank.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long>
{
}
