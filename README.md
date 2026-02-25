# forexrateapi

forexrateapi is the official Java wrapper for ForexRateAPI.com. This allows you to quickly integrate our foreign exchange rate API and currency conversion API into your application. Check https://forexrateapi.com documentation for more information.

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.forexrateapi</groupId>
    <artifactId>forexrateapi</artifactId>
    <version>1.3.0</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle`:

```groovy
implementation 'io.github.forexrateapi:forexrateapi:1.3.0'
```

## Usage

```java
import io.github.forexrateapi.client.ForexRateApiClient;

String apiKey = "SET_YOUR_API_KEY_HERE";
ForexRateApiClient client = new ForexRateApiClient(apiKey);

// Or use EU server:
// ForexRateApiClient client = new ForexRateApiClient(apiKey, "eu");
```
---
## Server Regions

ForexRateAPI provides two regional endpoints. Choose the one closest to your servers for optimal performance.

| Region | Base URL |
|--------|----------|
| United States (default) | `https://api.forexrateapi.com/v1` |
| Europe | `https://api-eu.forexrateapi.com/v1` |

```java
import io.github.forexrateapi.client.ForexRateApiClient;

// Default (US)
ForexRateApiClient client = new ForexRateApiClient("SET_YOUR_API_KEY_HERE");

// Europe
ForexRateApiClient client = new ForexRateApiClient("SET_YOUR_API_KEY_HERE", "eu");
```

---
## Documentation

#### fetchSymbols()
```java
client.fetchSymbols();
```

[Link](https://forexrateapi.com/documentation#api_symbol)

---
#### setServer(server)

- `server` <[string]> Pass `"eu"` to use the EU server (`api-eu.forexrateapi.com`), or `"us"` for the US server. Defaults to US if not specified.

```java
client.setServer("eu");
```

---
#### fetchLive(base, currencies, math)

- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currencies` <[List]<[string]>> Optional. Pass in a list of currencies to return values for.
- `math` <[string]> Optional. Pass in a math expression to apply to the rates.

```java
client.fetchLive("USD", List.of("AUD", "CAD", "GBP", "JPY"), "");
```

[Link](https://forexrateapi.com/documentation#api_realtime)

---
#### fetchHistorical(date, base, currencies)

- `date` <[string]> Required. Pass in a string with format `YYYY-MM-DD`
- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currencies` <[List]<[string]>> Optional. Pass in a list of currencies to return values for.

```java
client.fetchHistorical("2024-02-05", "USD", List.of("AUD", "CAD", "GBP", "JPY"));
```

[Link](https://forexrateapi.com/documentation#api_historical)

---
#### hourly(base, currency, startDate, endDate, math, dateType)

- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currency` <[string]> Required. Specify currency you would like to get hourly rates for.
- `startDate` <[string]> Required. Specify the start date using the format `YYYY-MM-DD`.
- `endDate` <[string]> Required. Specify the end date using the format `YYYY-MM-DD`.
- `math` <[string]> Optional. Pass in a math expression to apply to the rates.
- `dateType` <[string]> Optional. Pass in a date type, overrides date parameters if passed in.

```java
client.hourly("USD", "EUR", "2024-02-05", "2024-02-05", "", "");
```

[Link](https://forexrateapi.com/documentation#api_hourly)

---
#### fetchOHLC(base, currency, date, dateType)

- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currency` <[string]> Required. Specify currency you would like to get OHLC for.
- `date` <[string]> Required. Specify date to get OHLC for specific date using format `YYYY-MM-DD`.
- `dateType` <[string]> Optional. Pass in a date type, overrides date parameter if passed in.

```java
client.fetchOHLC("USD", "EUR", "2024-02-05", "");
```

[Link](https://forexrateapi.com/documentation#api_ohlc)

---
#### convert(fromCurrency, toCurrency, amount, date)

- `fromCurrency` <[string]> Optional. Pass in a base currency, defaults to USD.
- `toCurrency` <[string]> Required. Specify currency you would like to convert to.
- `amount` <[number]> Required. The amount to convert.
- `date` <[string]> Optional. Specify date to use historical midpoint value for conversion with format `YYYY-MM-DD`. Otherwise, it will use live exchange rate date if value not passed in.

```java
client.convert("USD", "EUR", 100, "2024-02-05");
```

[Link](https://forexrateapi.com/documentation#api_convert)

---
#### timeframe(startDate, endDate, base, currencies)

- `startDate` <[string]> Required. Specify the start date of your timeframe using the format `YYYY-MM-DD`.
- `endDate` <[string]> Required. Specify the end date of your timeframe using the format `YYYY-MM-DD`.
- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currencies` <[List]<[string]>> Optional. Pass in a list of currencies to return values for.

```java
client.timeframe("2024-02-05", "2024-02-06", "USD", List.of("AUD", "CAD", "GBP", "JPY"));
```

[Link](https://forexrateapi.com/documentation#api_timeframe)

---
#### change(startDate, endDate, base, currencies, dateType)

- `startDate` <[string]> Required. Specify the start date of your timeframe using the format `YYYY-MM-DD`.
- `endDate` <[string]> Required. Specify the end date of your timeframe using the format `YYYY-MM-DD`.
- `base` <[string]> Optional. Pass in a base currency, defaults to USD.
- `currencies` <[List]<[string]>> Optional. Pass in a list of currencies to return values for.
- `dateType` <[string]> Optional. Pass in a date type, overrides date parameters if passed in.

```java
client.change("2024-02-05", "2024-02-06", "USD", List.of("AUD", "CAD", "GBP", "JPY"), "");
```

[Link](https://forexrateapi.com/documentation#api_change)

---
#### usage()
```java
client.usage();
```

[Link](https://forexrateapi.com/documentation#api_usage)

---
**[Official documentation](https://forexrateapi.com/documentation)**

---
## FAQ

- How do I get an API Key?

    Free API Keys are available [here](https://forexrateapi.com).

- I want more information

    Checkout our FAQs [here](https://forexrateapi.com/faq).


## Support

For support, get in touch using [this form](https://forexrateapi.com/contact).


[List]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html 'List'
[number]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Number.html 'Number'
[string]: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html 'String'
