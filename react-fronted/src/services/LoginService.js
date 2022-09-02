import axios from 'axios';

const LOGIN_API_BASE_URL = "http://localhost:8080/api/v1/";

const LoginService = {
    login: function(username, password){
        
        const user = {
            "username": username,
            "password": password
        }

        return axios.post(LOGIN_API_BASE_URL, user);
    }
};

export default LoginService;