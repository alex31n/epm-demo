package com.bits.epm.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoindeskDTO {

    public Time time;
    public String disclaimer;
    public String chartName;
    public Bpi bpi;

    @Data
    public static class Bpi{
        @JsonProperty("USD")
        public Currency uSD;
        @JsonProperty("GBP")
        public Currency gBP;
        @JsonProperty("EUR")
        public Currency eUR;
    }

    @Data
    public static class Time{
        public String updated;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        public Date updatedISO;
        public String updateduk;
    }

    @Data
    public static class Currency {
        public String code;
        public String symbol;
        public String rate;
        public String description;
        @JsonProperty("rate_float")
        public double rateFloat;
    }

}
