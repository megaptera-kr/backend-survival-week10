import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export default function useSignUp() {
  const signUp = async ({ username, password }) => {
    await axios.post(`${BASE_URL}/users`, { username, password });
  };

  return {
    signUp,
  };
}
