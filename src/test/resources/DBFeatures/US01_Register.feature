@US01_DBTest
Feature: Aday öğrenciler sisteme kayıt olabilmelidir. DB

  Scenario Outline: US01_GuestUser

    Given "<username>" ile guest useri cagir
    Then body sunlari icermeli: "<name>", "<surname>", "<birthplace>", "<phone>", "<gender>", "<Date Of Birth>", "<ssn>","<username>","<password>"
    Examples:
      | username  | name | surname | birthplace | phone        | gender | Date Of Birth | ssn         | password   |
      | alican.987 | Ali  | Can     | Ankara     | 333-555-1255 | 0      | 2023-06-01    | 333-66-0922 | Alican.123 |


    #Loop Deneme Scenariosu
  @LoopDB
  Scenario: Loop Deneme Scenariosu
    Given birthplace "batch129" ile guest useri cagir
    Then kac tane user oldugunu bul

  @DBNegative
  Scenario: Silinen guest useri dogrula
    Given silinen "alican129" ile guest useri cagir
    Then tablein bos oldugunu dogrula