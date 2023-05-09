import axios from 'axios';

import useSWR from 'swr';

import useAccessToken from './useAccessToken';

const BASE_URL = 'http://localhost:8080';

const fetcher = ({ path, accessToken }) => axios
  .get(`${BASE_URL}/${path}`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  })
  .then((response) => response.data);

export default function useFetchCart() {
  const { accessToken } = useAccessToken();

  const { data, mutate } = useSWR(
    accessToken ? ({
      path: 'cart-line-items',
      accessToken,
    }) : null,
    fetcher,
  );

  const { lineItems } = data || {};

  return {
    lineItems,
    reload: mutate,
  };
}
