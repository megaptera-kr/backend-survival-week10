import { useNavigate } from 'react-router-dom';

import useLoginRequired from '../hooks/useLoginRequired';
import useLogout from '../hooks/useLogout';

import Admin from '../components/Admin';

export default function AdminPage() {
  const result = useLoginRequired({ url: '/admin' });

  if (result) {
    return result;
  }

  const navigate = useNavigate();

  const { logout } = useLogout();

  const handleClickLogout = async () => {
    await logout();

    navigate('/login?redirect=/admin');
  };

  return (
    <Admin onClickLogout={handleClickLogout} />
  );
}
