package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Stock.Response.StockResponseDto;
import com.eustache.pos_system.DTO.Stock.Response.StockSummary;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Entities.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    /**
     * Converts a Stock entity to a StockResponseDto.
     * @param stock the Stock entity to convert
     * @return a StockResponseDto containing the converted data
     */
    public StockResponseDto toResponseFromStock(Stock stock){
        Product product = stock.getProduct();
        String productName = product.getName();
        return new StockResponseDto(
                stock.getId(),
                stock.getQuantity(),
                stock.getMinQuantity(),
                stock.getLastUpdated(),
                stock.getCreatedAt(),
                stock.getUpdatedAt(),
                product.getId(),
                productName
        );
    }

    /**
     * Converts a Stock entity to a StockSummary.
     * @param stock the Stock entity to convert
     * @return a StockSummary containing the converted data
     */
    public StockSummary toSummary(Stock stock){
        return new StockSummary(
                stock.getQuantity(),
                stock.getMinQuantity(),
                stock.getLastUpdated()
        );
    }
}
