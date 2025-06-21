package com.example.demo.Controller;

import com.example.demo.Model.Currency;
import com.example.demo.Service.CurrencyService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    final private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Tag(name = "Retrieving")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved currency"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Currency not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
            @ApiResponse(responseCode = "504", description = "Gateway timeout", content = @Content),
    })
    @GetMapping("/{code}/{startDate}/{endDate}")
    public ResponseEntity<Currency> getAverageCurrencyValue(@PathVariable String code, @PathVariable String startDate, @PathVariable String endDate) {
        Currency currency = currencyService.getAvgCurrencyValue(code, startDate, endDate);

        if (currency != null) {
            return ResponseEntity.ok(currency);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
