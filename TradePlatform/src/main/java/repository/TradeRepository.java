package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer>
{
 
}
