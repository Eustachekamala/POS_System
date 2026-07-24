package com.eustache.pos_system.Services.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SalesReportService {

    // DASHBOARD / REPORTS

    BigDecimal getTodayRevenue();
    BigDecimal getRevenueBetweenDates(
            LocalDate startDate,
            LocalDate endDate
    );
    Long getTodaySalesCount();
    Long getSalesCountBetweenDates(
            LocalDate startDate,
            LocalDate endDate
    );


}
