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


### 📌 **How this code works:**

#### **1️⃣ Table: `USER`** (Users)
| Field      | Type       | Description            |
|------------|-----------|------------------------|
| `id`       | `INT PK`  | Unique user identifier |
| `name`     | `STRING`  | User's name            |
| `pass`     | `STRING`  | User's password        |

---

#### **2️⃣ Table: `SHELTER`** (Animal Shelters)
| Field      | Type       | Description               |
|------------|-----------|---------------------------|
| `id`       | `INT PK`  | Unique shelter identifier |
| `name`     | `STRING`  | Shelter's name           |
| `pass`     | `STRING`  | Shelter's password       |

---

#### **3️⃣ Table: `ANIMAL`** (Animals)
| Field       | Type       | Description                          |
|------------|-----------|--------------------------------------|
| `reiac`    | `INT PK`  | Unique animal identifier            |
| `name`     | `STRING`  | Animal’s name                       |
| `age`      | `INT`     | Animal’s age                        |
| `gender`   | `STRING`  | Animal’s gender                     |
| `weight`   | `FLOAT`   | Animal’s weight                     |
| `id_shelter` | `INT FK` | Shelter where the animal is housed  |

---

#### **4️⃣ Table: `CONTACT`** (Users Contact Shelters)
> Many-to-many relationship between `USER` and `SHELTER` (only users can initiate contact).

| Field       | Type       | Description                          |
|------------|-----------|--------------------------------------|
| `id`       | `INT PK`  | Unique contact identifier           |
| `id_user`  | `INT FK`  | User who initiates contact          |
| `id_shelter` | `INT FK` | Shelter being contacted            |
| `date`     | `DATETIME`| Date when contact was made          |

---

#### **5️⃣ Table: `ANSWER`** (Shelters Respond to Users)
> Many-to-many relationship between `SHELTER` and `USER` (only shelters can respond to users).

| Field       | Type       | Description                         |
|------------|-----------|-------------------------------------|
| `id`       | `INT PK`  | Unique answer identifier           |
| `id_shelter` | `INT FK` | Shelter sending the response      |
| `id_user`  | `INT FK`  | User receiving the response       |
| `message`  | `TEXT`    | Message content                   |
| `date`     | `DATETIME`| Date when the response was sent   |

---

### 📌 **Final Relationship Overview:**
- **A user can view multiple animals** → An optional table (`USER_ANIMAL`) could store this.  
- **A user can contact multiple shelters, and a shelter can be contacted by multiple users** → `CONTACT`.  
- **A shelter can respond to multiple users, and a user can receive responses from multiple shelters** → `ANSWER`.  
- **A shelter can publish multiple animals, but each animal belongs to only one shelter** → `ANIMAL` (1:N relationship with `SHELTER`).  

---

### 🚀 **Conclusion**
- **5 tables** are created (`USER`, `SHELTER`, `ANIMAL`, `CONTACT`, `ANSWER`).  
- **`CONTACT`** tracks when a user initiates contact with a shelter.  
- **`ANSWER`** tracks responses from shelters to users.  
- **`ANIMAL`** is linked to `SHELTER` with a **one-to-many** relationship.  
