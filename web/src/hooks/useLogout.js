import axios from 'axios';
import useAccessToken from './useAccessToken';

const BASE_URL = 'http://localhost:8080';

export default function useLogout() {
  const { accessToken, setAccessToken } = useAccessToken();

  const logout = async () => {
    await axios.delete(`${BASE_URL}/session`, {
      headers: { Authorization: `Bearer ${accessToken}` },
    });

    setAccessToken('');
  };

  return {
    logout,
  };
}
