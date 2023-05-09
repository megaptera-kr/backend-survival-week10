import axios from 'axios';

import useSWR from 'swr';

const BASE_URL = 'http://localhost:8080';

const fetcher = (path) => axios
  .get(`${BASE_URL}/${path}`)
  .then((response) => response.data);

export default function useFetchProducts() {
  const { data, mutate } = useSWR('products', fetcher);

  const { products } = data || {};

  return {
    products,
    reload: mutate,
  };
}
