import axios from 'axios';
import useAccessToken from './useAccessToken';

const BASE_URL = 'http://localhost:8080';

export default function useAddCartItem() {
  const { accessToken } = useAccessToken();

  const addCartItem = async ({ productId }) => {
    await axios.post(
      `${BASE_URL}/cart-line-items`,
      { productId, quantity: 1 },
      {
        headers: { Authorization: `Bearer ${accessToken}` },
      },
    );
  };

  const changeQuantity = async ({ id, quantity }) => {
    await axios.patch(
      `${BASE_URL}/cart-line-items/${id}`,
      { quantity },
      {
        headers: { Authorization: `Bearer ${accessToken}` },
      },
    );
  };

  return {
    addCartItem,
    changeQuantity,
  };
}
