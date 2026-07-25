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

    /**
     * Gets the revenue for today.
     * @return BigDecimal
     */
    @Override
    public BigDecimal getTodayRevenue() {
        LocalDate today = LocalDate.now();
        return saleRepository.findRevenueBetweenDates(
                startOfDay(today),
                endOfDay(today)
        );
    }

    /**
     * Gets the revenue between two dates.
     * @param startDate LocalDate
     * @param endDate LocalDate
     * @return BigDecimal
     */
    @Override
    public BigDecimal getRevenueBetweenDates(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findRevenueBetweenDates(
                startOfDay(startDate),
                endOfDay(endDate)
        );
    }

    /**
     * Gets the sales count for today.
     * @return Long
     */
    @Override
    public Long getTodaySalesCount() {
        LocalDate today = LocalDate.now();
        return saleRepository.findSalesCountBetweenDates(
                startOfDay(today),
                endOfDay(today)
        );
    }

    /**
     * Gets the sales count between two dates.
     * @param startDate LocalDate
     * @param endDate LocalDate
     * @return Long
     */
    @Override
    public Long getSalesCountBetweenDates(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findSalesCountBetweenDates(
                startOfDay(startDate),
                endOfDay(endDate)
        );
    }

    /**
     * Gets the start of day for a given date.
     * @param date LocalDate
     * @return LocalDateTime
     */
    private LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    /**
     * Gets the end of day for a given date.
     * @param date LocalDate
     * @return LocalDateTime
     */
    private LocalDateTime endOfDay(LocalDate date) {
        return date.plusDays(1).atStartOfDay();
    }
}
