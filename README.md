# Spring Boot LocaleResolver Entity Translating

## API Reference

#### Get FAQ list

default lang is: en_US

```http
  GET /faq
  GET /faq?lang=tr
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `lang` | `string` |  Your language slug |

Response

```json
[
  {
    "id": 1,
    "faqId": 1,
    "languageId": 2,
    "question": "Question in English",
    "questionDetail": "Question detail in English",
    "questionAnswer": "Question answer in English"
  },
  {
    "id": 3,
    "faqId": 2,
    "languageId": 2,
    "question": "Question2 in English",
    "questionDetail": "Question2 detail in English",
    "questionAnswer": "Question2 answer in English"
  }
]
```

#### Get FAQ translation by FAQ id

default lang is: en_US

```http
  GET /faq/translation/2?lang=tr
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `lang` | `string` |  Your language slug |

Response

```json
{
  "id": 4,
  "faqId": 2,
  "languageId": 1,
  "question": "Question2 in Turkish",
  "questionDetail": "Question2 detail in Turkish",
  "questionAnswer": "Question2 answer in Turkish"
}
```

#### Get FAQ by id

default lang is: en_US

```http
  GET /faq/1?lang=tr
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `lang` | `string` |  Your language slug |

Response (brings all translations of FAQ by given id)

```json
  [
  {
    "id": 1,
    "question": "Question in English",
    "questionAnswer": "Question answer in English",
    "questionDetail": "Question detail in English",
    "languageSlug": "en_US"
  },
  {
    "id": 2,
    "question": "Question in Turkish",
    "questionAnswer": "Question answer in Turkish",
    "questionDetail": "Question detail in Turkish",
    "languageSlug": "tr"
  }
]
```
