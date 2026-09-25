# Lab 04 — JUnit 5 Unit Testing

**Оюутан:** О.Мэнд-Амар  
**Оюутны код:** B232270001

---

## 1. Лабораторийн зорилго

Энэхүү лабораторийн ажлын зорилго нь **JUnit 5** framework ашиглан Java application-д unit test боловсруулах, boundary value болон exception нөхцөлүүдийг шалгах, мөн mutation testing ашиглан бичсэн тестүүдийн чанарыг баталгаажуулахад оршино.

Лабораторийн хүрээнд `GradeCalculator` класс болон түүнд харгалзах `GradeCalculatorTest` unit test классыг боловсруулсан.

Үндсэн зорилтууд:

- JUnit 5 ашиглан unit test бичих
- Энгийн зөв ажиллагааг шалгах
- Boundary value нөхцөлүүдийг шалгах
- Exception нөхцөлүүдийг шалгах
- `assertThrows()` ашиглах
- Maven ашиглан тест ажиллуулах
- Mutation testing хийх
- Тестийн үр дүнг файлд хадгалах
- Git ашиглан ажлын явцыг үе шаттай commit хийх

---

## 2. Орчны мэдээлэл

### 2.1 Java

Төслийг Java 17 release-д зориулан тохируулсан.

`java -version` командын үр дүн:

```text
openjdk version "17.0.19" 2026-04-21
OpenJDK Runtime Environment Homebrew (build 17.0.19+0)
OpenJDK 64-Bit Server VM Homebrew (build 17.0.19+0, mixed mode, sharing)
```

### 2.2 Maven

`mvn -version` командын үр дүн:

```text
Apache Maven 3.9.16
Java version: 26.0.1
Maven home: /opt/homebrew/Cellar/maven/3.9.16/libexec
OS name: "mac os x"
arch: "aarch64"
```

Maven ажиллах орчинд Java 26 ашиглаж байгаа боловч төслийн `pom.xml` файлд:

```xml
<maven.compiler.release>17</maven.compiler.release>
```

гэж тохируулсан. Ингэснээр төслийн source болон target compilation нь Java 17 release-д хийгдэнэ.

---

## 3. Ашигласан технологи

| Технологи             | Хувилбар / Тайлбар |
| --------------------- | ------------------ |
| Java                  | 17 release         |
| Maven                 | 3.9.16             |
| JUnit                 | 5.10.2             |
| Maven Compiler Plugin | 3.13.0             |
| Maven Surefire Plugin | 3.2.5              |
| Git                   | Version control    |
| GitHub                | Remote repository  |
| Operating System      | macOS              |
| Architecture          | aarch64            |

---

## 4. Төслийн бүтэц

Төслийн үндсэн бүтэц:

```text
lab04-junit/
├── .gitignore
├── pom.xml
├── README.md
│
├── results/
│   ├── mvn-test.txt
│   └── mvn-test-mutant.txt
│
└── src/
    ├── main/
    │   └── java/
    │       └── mn/
    │           └── edu/
    │               └── must/
    │                   └── sqat/
    │                       └── GradeCalculator.java
    │
    └── test/
        └── java/
            └── mn/
                └── edu/
                    └── must/
                        └── sqat/
                            └── GradeCalculatorTest.java
```

`target/`, `.idea/`, `.vscode/` болон `.DS_Store` файлуудыг Git repository-д оруулахгүй байхаар `.gitignore` файлд тохируулсан.

---

## 5. Maven болон JUnit 5 тохиргоо

`pom.xml` файлд Java release-ийг 17 болгон тохируулсан:

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>17</maven.compiler.release>
</properties>
```

JUnit 5 dependency:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

Maven Surefire Plugin:

```xml
<plugin>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

Archetype-оос үүссэн `App.java` болон `AppTest.java` файлуудыг устгаж, лабораторийн шаардлагад тохирсон `GradeCalculator.java` болон `GradeCalculatorTest.java` файлуудаар сольсон.

---

## 6. GradeCalculator классын үйл ажиллагаа

`GradeCalculator` класс нь нийт оноо тооцох болон үсгэн дүн тодорхойлох хоёр үндсэн үйлдэлтэй.

### 6.1 totalScore()

`totalScore()` метод нь гурван оноог нийлбэрлэж нийт оноог буцаана.

Хэрэв:

- нийт оноо сөрөг байвал
- нийт оноо 100-аас их байвал

`IllegalArgumentException` үүсгэнэ.

Эдгээр хоёр нөхцөлийг тус тусад нь unit test-ээр шалгасан.

### 6.2 letterGrade()

`letterGrade()` метод нь оноонд үндэслэн дараах үсгэн дүнг буцаана.

|   Оноо | Дүн |
| -----: | :-: |
| 90–100 |  A  |
|  80–89 |  B  |
|  70–79 |  C  |
|  60–69 |  D  |
|   0–59 |  F  |

Ялангуяа `90`, `80`, `70`, `60`, `59` зэрэг boundary утгуудыг тестээр шалгасан.

---

## 7. Unit test-үүд

Нийт **8 тестийн метод** боловсруулсан.

### 7.1 Нийт онооны зөв тооцоолол

```text
totalScoreShouldReturnCorrectTotal
```

`30 + 25 + 30 = 85` гэсэн зөв тооцооллыг шалгана.

### 7.2 Сөрөг нийт оноо

```text
totalScoreShouldThrowExceptionWhenTotalIsNegative
```

Сөрөг утга өгсөн үед:

```text
IllegalArgumentException
```

үүсэж байгаа эсэхийг `assertThrows()` ашиглан шалгана.

### 7.3 100-аас их нийт оноо

```text
totalScoreShouldThrowExceptionWhenTotalExceeds100
```

Нийт оноо 100-аас их болсон үед `IllegalArgumentException` үүсэж байгаа эсэхийг шалгана.

### 7.4 Boundary value тестүүд

Дараах тестүүд нь дүнгийн хязгааруудыг шалгана.

```text
letterGradeShouldReturnAFor90
letterGradeShouldReturnBFor80
letterGradeShouldReturnCFor70
letterGradeShouldReturnDFor60
letterGradeShouldReturnFFor59
```

Ингэснээр `90 → A`, `80 → B`, `70 → C`, `60 → D`, `59 → F` гэсэн boundary нөхцөлүүдийг шалгасан.

---

## 8. Тестийн тоо

JUnit 5 test class-д нийт 8 `@Test` метод байна.

```text
@Test methods = 8
```

Тестийг Maven ашиглан ажиллуулахад:

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

гарсан.

Тиймээс үндсэн тестүүд бүгд амжилттай ажилласан.

---

## 9. Үндсэн тестийн үр дүн

Тестийг дараах командаар ажиллуулсан:

```bash
mkdir -p results && mvn test 2>&1 | tee results/mvn-test.txt
```

Үр дүн:

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
```

Maven build:

```text
BUILD SUCCESS
```

Үндсэн тестийн бүрэн үр дүнг дараах файлаас үзэж болно:

**[mvn-test.txt](results/mvn-test.txt)**

---

## 10. Mutation Testing

Тестүүд boundary нөхцөлийг үнэхээр шалгаж байгаа эсэхийг баталгаажуулахын тулд mutation testing хийсэн.

Анхны implementation:

```java
if (score >= 90) {
    return "A";
}
```

Mutation testing хийх үед зориуд:

```java
if (score > 90) {
    return "A";
}
```

болгон өөрчилсөн.

Ингэснээр яг `90` оноо `A` биш `B` болж өөрчлөгдөнө.

### Mutation-ийн үр дүн

Mutation хийсний дараа Maven test ажиллуулахад:

```text
Tests run: 8, Failures: 1, Errors: 0, Skipped: 0
```

Дараах тест унасан:

```text
letterGradeShouldReturnAFor90
```

Алдааны мэдээлэл:

```text
expected: <A> but was: <B>
```

Энэ нь `letterGradeShouldReturnAFor90` тест нь `90 → A` boundary нөхцөлийг зөв илрүүлж байгааг харуулж байна.

Mutation test-ийн бүрэн үр дүн:

**[mvn-test-mutant.txt](results/mvn-test-mutant.txt)**

---

## 11. Mutation Testing-ийн дүгнэлт

Mutation хийх үед `score >= 90` нөхцөлийг `score > 90` болгон өөрчлөхөд `90` онооны тест амжилтгүй болсон.

Иймээс:

```text
90 → A
```

гэсэн boundary нөхцөл нь unit test-ээр хамгаалагдсан болох нь батлагдсан.

Өөрөөр хэлбэл тестийн багц нь зөвхөн энгийн input-уудыг шалгахаас гадна boundary condition-ийн өөрчлөлтийг илрүүлж чадсан.

---

## 12. Test case summary

|   № | Test method                                         | Шалгах нөхцөл   |
| --: | --------------------------------------------------- | --------------- |
|   1 | `totalScoreShouldReturnCorrectTotal`                | Зөв нийлбэр     |
|   2 | `totalScoreShouldThrowExceptionWhenTotalIsNegative` | Сөрөг утга      |
|   3 | `totalScoreShouldThrowExceptionWhenTotalExceeds100` | 100-аас их утга |
|   4 | `letterGradeShouldReturnAFor90`                     | 90 → A          |
|   5 | `letterGradeShouldReturnBFor80`                     | 80 → B          |
|   6 | `letterGradeShouldReturnCFor70`                     | 70 → C          |
|   7 | `letterGradeShouldReturnDFor60`                     | 60 → D          |
|   8 | `letterGradeShouldReturnFFor59`                     | 59 → F          |

---

## 13. Test execution evidence

Repository дотор `results/` хавтсыг зориуд хадгалсан.

```text
results/
├── mvn-test.txt
└── mvn-test-mutant.txt
```

### Үндсэн тест

**[Үндсэн тестийн үр дүн — mvn-test.txt](results/mvn-test.txt)**

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Mutation test

**[Mutation test-ийн үр дүн — mvn-test-mutant.txt](results/mvn-test-mutant.txt)**

```text
Tests run: 8, Failures: 1, Errors: 0, Skipped: 0
expected: <A> but was: <B>
```

---

## 14. Git version control

Лабораторийн ажлын явцыг Git ашиглан үе шаттай хадгалсан.

Commit history:

```text
95d4eeb Add lab documentation
127f6a5 Add mutation test evidence
6192350 Set up JUnit 5 lab
fb76dd3 Initialize Maven JUnit project
```

Үндсэн үе шатууд:

1. Maven JUnit project үүсгэсэн
2. JUnit 5 test болон `GradeCalculator` implementation нэмсэн
3. Mutation test хийж үр дүнг хадгалсан
4. README documentation нэмсэн

---

## 15. Repository-ийн төлөв

Лабораторийн ажлын эцсийн байдлаар:

```text
git status
```

үр дүн:

```text
On branch master
nothing to commit, working tree clean
```

Мөн `target/` хавтас repository-д ороогүй.

`.gitignore`:

```text
target/
.idea/
.vscode/
.DS_Store
```

---

## 16. AI ашигласан байдал

Лабораторийн ажлыг боловсруулах явцад AI хэрэгслийг туслах хэрэгсэл болгон ашигласан.

AI-аас дараах чиглэлээр зөвлөгөө авсан:

- JUnit 5 unit test-ийн бүтэц боловсруулах
- `assertThrows()` ашиглан exception test бичих
- Boundary value testing-ийн жишээ болон test case тодорхойлох
- Maven болон Surefire-ийн test execution-ийн үр дүнг тайлбарлах
- Mutation testing-ийн зарчмыг ойлгох
- Git commit болон repository-ийн зохион байгуулалтын талаар зөвлөгөө авах
- README файлын бүтэц, тайлбарын хэлбэрийг боловсруулах

AI-ийн өгсөн зөвлөмжийг шууд эцсийн үр дүн гэж ашиглаагүй бөгөөд төслийн кодыг ажиллуулж, Maven test болон mutation test-ийг өөрийн хөгжүүлэлтийн орчинд ажиллуулан үр дүнг шалгасан.

Тестийн бодит үр дүнг repository-д хадгалсан `results/mvn-test.txt` болон `results/mvn-test-mutant.txt` файлуудаар баталгаажуулсан.

---

## 17. Дүгнэлт

Энэхүү лабораторийн ажлаар JUnit 5 ашиглан `GradeCalculator` классын үйл ажиллагааг unit test-ээр шалгасан.

Нийт 8 test method боловсруулж:

- зөв тооцоолол
- сөрөг утгын exception
- 100-аас их утгын exception
- A, B, C, D, F дүнгийн boundary нөхцөлүүд

зэргийг шалгасан.

Үндсэн тестийн үед бүх 8 тест амжилттай ажиллаж:

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

үр дүн гарсан.

Мөн `score >= 90` нөхцөлийг зориуд `score > 90` болгон mutation хийхэд `90 → A` boundary test амжилтгүй болсон. Энэ нь бичсэн тестүүд тухайн boundary нөхцөлийг илрүүлж чаддаг болохыг баталсан.

Ингэснээр JUnit 5 unit testing, exception testing, boundary value testing болон mutation testing-ийн үндсэн зарчмуудыг практик байдлаар хэрэгжүүлсэн.
