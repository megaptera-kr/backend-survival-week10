import axios from 'axios';
import useAccessToken from './useAccessToken';

const BASE_URL = 'http://localhost:8080';

export default function useLogin() {
  const { setAccessToken } = useAccessToken();

  const login = async ({ username, password }) => {
    try {
      const { data } = await axios.post(`${BASE_URL}/session`, {
        username,
        password,
      });

      const { accessToken, roles } = data;
      setAccessToken(accessToken);

      return roles;
    } catch (e) {
      return null;
    }
  };

  return {
    login,
  };
}
