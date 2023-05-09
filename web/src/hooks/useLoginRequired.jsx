import { useLocalStorage } from 'usehooks-ts';

import { Navigate } from 'react-router-dom';

export default function useLoginRequired({ url }) {
  const [accessToken] = useLocalStorage('accessToken', '');

  if (accessToken) {
    return null;
  }

  return (
    <Navigate to={`/login?redirect=${url}`} replace />
  );
}
