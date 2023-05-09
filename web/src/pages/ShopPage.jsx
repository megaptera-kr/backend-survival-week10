import { useNavigate } from 'react-router-dom';

import useAccessToken from '../hooks/useAccessToken';
import useLogout from '../hooks/useLogout';

import Shop from '../components/Shop';

export default function ShopPage() {
  const navigate = useNavigate();

  const { accessToken } = useAccessToken();

  const { logout } = useLogout();

  const handleClickLogin = () => {
    navigate('/login');
  };

  const handleClickLogout = async () => {
    await logout();

    navigate('/');
  };

  return (
    <Shop
      loggedIn={!!accessToken}
      onClickLogin={handleClickLogin}
      onClickLogout={handleClickLogout}
    />
  );
}
