import axios from 'axios';
import useAccessToken from './useAccessToken';

const BASE_URL = 'http://localhost:8080';

export default function useRegisterProduct() {
  const { accessToken } = useAccessToken();

  const registerProduct = async (data) => {
    await axios.post(
      `${BASE_URL}/products`,
      data,
      {
        headers: { Authorization: `Bearer ${accessToken}` },
      },
    );
  };

  return {
    registerProduct,
  };
}
