<div align="center">
    <img width="200" src="https://avatars.githubusercontent.com/u/152792155?v=4" alt="Batik Discover">
<h1>Batik Discover<h1>
</div>

# Batik Discover API
**API URL:**
- https://batik-discover-predict-dot-capstone-406813.et.r.appspot.com/
- https://batik-discover-backend-dot-capstone-406813.et.r.appspot.com/

**API Source Code:**
- https://github.com/BatikDiscover/CH2-PS500/tree/Cloud-Computing
- https://github.com/BatikDiscover/CH2-PS500/tree/Ml-Connector

### Endpoint
| Method | Path | Description |
| --- | --- | --- |
| `POST` | `/register` | Register new user |
| `POST` | `/login` | Login user with email and password |
| `POST` | `/logout` | Logout user |
| `GET` | `/user/{id}` | Get user by id |
| `GET` | `/batik` | Get all batik |
| `GET` | `/batik{id}` | Get batik detail |
| `POST` | `/batik/{id}/save` | Add batik to favorite |
| `GET` | `/batik/save/` | Get favorite batik by user id |
| `DELETE` | `/batik/{id}/save` | Remove batik from favorite |
| `POST` | `/post` | Create a new post to community forum |
| `GET` | `/post` | Get all post |
| `GET` | `/post/{id}` | Get detail post |
| `DELETE` | `/post/{id}` | Delete post |
| `POST` | `/post/{id}/comment` | Add comment to post |
| `GET` | `/post/{id}/comment` | Get all comment by post |
| `DELETE` | `/post/{id}/comment/{commentId}` | Delete comment |
| `POST` | `/post/{id}/like` | Like a post |
| `GET` | `/post/{id}/like` | Get number of like of a post |
| `DELETE` | `/post/{id}/like` | Unlike post |
| `GET` | `/article` | Get all article |
| `GET` | `/article/{id}` | Get detail article |
| `POST` | `/article/{id}/like` | Like an article |
| `GET` | `/article/{id}/like` | Get number of like of an article |
| `DELETE` | `/article/{id}/like` | Unlike article |
| `GET` | `/event` | Get all event |
| `GET` | `/event/{id}` | Get detail event |
| `POST` | `/event/{id}/save` | Save an event |
| `GET` | `/event/save` | Get saved event by user id |
| `DELETE` | `/event/{id}/save` | Unsave event |
| `POST` | `/predicts` | Detect batik patern |


### Developmet
- Language: Javascript (Hapi.js), Python (Flask)
- Database: Firebase Firestore, Firebase Storage
- Deploy  : Google Cloud App Engine 
