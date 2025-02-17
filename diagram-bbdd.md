```mermaid
erDiagram
    USER {
        int id PK
        string name
        string pass
    }
    
    SHELTER {
        int id PK
        string name
        string pass       
    }
    
    ANIMAL {
        int reiac PK
        string name
        int age
        string gender
        float weight
        int id_shelter FK
    }
    
    USER ||--o{ ANIMAL : "views"
    USER ||--o{ CONTACT : "contacts"
    CONTACT }o--|| SHELTER : "with"
    SHELTER ||--o{ ANSWER : "answers"
    ANSWER }o--|| USER : "to"
    SHELTER ||--o{ ANIMAL : "publishes"
