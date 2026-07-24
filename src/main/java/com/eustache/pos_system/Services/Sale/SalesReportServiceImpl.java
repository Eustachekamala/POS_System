package com.eustache.pos_system.Services.Sale;

import com.eustache.pos_system.Repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesReportServiceImpl implements SalesReportService{
    private final SaleRepository saleRepository;

    @Override
    public BigDecimal getTodayRevenue() {
        LocalDate today = LocalDate.now();
        return saleRepository.findRevenueBetweenDates(
                startOfDay(today),
                endOfDay(today)
        );
    }

    @Override
    public BigDecimal getRevenueBetweenDates(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findRevenueBetweenDates(
                startOfDay(startDate),
                endOfDay(endDate)
        );
    }

    @Override
    public Long getTodaySalesCount() {
        LocalDate today = LocalDate.now();
        return saleRepository.findSalesCountBetweenDates(
                startOfDay(today),
                endOfDay(today)
        );
    }

    @Override
    public Long getSalesCountBetweenDates(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findSalesCountBetweenDates(
                startOfDay(startDate),
                endOfDay(endDate)
        );
    }

    private LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    private LocalDateTime endOfDay(LocalDate date) {
        return date.plusDays(1).atStartOfDay();
    }
}
