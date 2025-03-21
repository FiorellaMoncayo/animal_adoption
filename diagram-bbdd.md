## 📌 Relational Schema Description

This diagram represents the structure of the database with five main tables:

- **USER**: Stores user information.
- **SHELTER**: Represents animal shelters.
- **ANIMAL**: Contains data about each animal and the shelter it belongs to.
- **CONTACT**: Records the contacts initiated by users with shelters.
- **ANSWER**: Stores the responses from shelters to users.

---

```mermaid
erDiagram
    USER {
        INT id PK
        STRING name
        STRING pass
    }
    
    SHELTER {
        INT id PK
        STRING name
        STRING pass       
    }
    
    ANIMAL {
        INT reiac PK
        STRING name
        INT age
        STRING gender
        FLOAT weight
        INT id_shelter FK
    }
    
    CONTACT {
        INT id PK
        INT id_user FK
        INT id_shelter FK
        TEXT request
        DATETIME dateStart
        TEXT answer
        DATETIME dateEnd
    }

    USER ||--o{ CONTACT : "contacts"
    CONTACT }o--|| SHELTER : "with"
    SHELTER ||--o{ ANIMAL : "publishes"
```

---

📌 **Summary of relationships:**  
- A user **can view multiple animals**.  
- A user **can contact multiple shelters** and **vice versa**.  
- A shelter **can respond to multiple users**.  
- A shelter **can publish multiple animals**, but each animal **belong to only one shelter**.  

