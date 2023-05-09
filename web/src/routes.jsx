import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import ShopPage from './pages/ShopPage';
import AdminPage from './pages/AdminPage';

const routes = [
  {
    children: [
      { path: '/', element: <ShopPage /> },
      { path: '/login', element: <LoginPage /> },
      { path: '/join', element: <SignUpPage /> },
      { path: '/admin', element: <AdminPage /> },
    ],
  },
];

export default routes;
