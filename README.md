# Lab 04 — JUnit 5 Unit Testing

**Оюутан:** О.Мэнд-Амар

**Оюутны код:** B232270001

## Орчны мэдээлэл

### Java

```text
openjdk version "17.0.19" 2026-04-21
OpenJDK Runtime Environment Homebrew (build 17.0.19+0)
OpenJDK 64-Bit Server VM Homebrew (build 17.0.19+0, mixed mode, sharing)
```

### Maven

```text
Apache Maven 3.9.16
Java version: 26.0.1
OS name: "mac os x"
arch: "aarch64"
```

## Лабораторийн зорилго

JUnit 5 ашиглан Java application-ийн unit test бичиж, boundary value болон exception нөхцөлүүдийг шалгах.

## Тестийн үр дүн

Нийт **8 тестийн метод** бичсэн.

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

Үндсэн тестийн үр дүн:

```text
BUILD SUCCESS
```

Mutation test-ийн үед `letterGrade` методын `score >= 90` нөхцөлийг зориуд `score > 90` болгож өөрчилсөн.

Энэ өөрчлөлтийн үед `90` оноо `A` байх ёстой гэсэн тест амжилтгүй болсон:

```text
Tests run: 8, Failures: 1, Errors: 0, Skipped: 0
```

Алдаа гарсан тест:

```text
letterGradeShouldReturnAFor90
expected: <A> but was: <B>
```

Mutation test нь тестийн `90 → A` boundary нөхцөлийг илрүүлж байгааг баталсан.

## Тестүүд

`GradeCalculator` класст дараах 8 unit test бичсэн:

1. `totalScoreShouldReturnCorrectTotal`
2. `totalScoreShouldThrowExceptionWhenTotalIsNegative`
3. `totalScoreShouldThrowExceptionWhenTotalExceeds100`
4. `letterGradeShouldReturnAFor90`
5. `letterGradeShouldReturnBFor80`
6. `letterGradeShouldReturnCFor70`
7. `letterGradeShouldReturnDFor60`
8. `letterGradeShouldReturnFFor59`

## Үр дүнгийн файлууд

Тестийн үр дүнг `results/` хавтсанд хадгалсан.

- `results/mvn-test.txt` — үндсэн тестийн үр дүн
- `results/mvn-test-mutant.txt` — mutation test-ийн үр дүн
