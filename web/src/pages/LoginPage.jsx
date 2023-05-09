import { useState } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import useLogin from '../hooks/useLogin';

import InputField from '../components/InputField';

const initState = {
  id: '',
  password: '',
  error: '',
};

export default function LoginPage() {
  const { search } = useLocation();
  const searchParams = new URLSearchParams(search);
  const redirect = searchParams.get('redirect') || '/';

  const navigate = useNavigate();

  const [state, setState] = useState(initState);

  const { login } = useLogin();

  const handleChangeState = ({ key, value }) => {
    setState({
      ...state,
      [key]: value,
    });
  };

  const handleClickLogin = async () => {
    const role = await login({
      username: state.id,
      password: state.password,
    });

    if (!role) {
      setState({
        ...state,
        error: '아이디와 패스워드를 확인해주세요!',
      });

      return;
    }

    setState(initState);

    if (redirect === '/admin') {
      if (!role.includes('ROLE_ADMIN')) {
        setState({
          ...state,
          error: '권한이 없습니다!',
        });
        return;
      }
    }

    navigate(redirect);
  };

  const handleClickSignUp = () => {
    navigate('/join');
  };

  return (
    <div>
      <h2>메가테라 쇼핑몰 로그인</h2>
      <InputField
        label="아이디"
        name="id"
        value={state.id}
        onChange={handleChangeState}
      />
      <InputField
        type="password"
        label="비밀번호"
        name="password"
        value={state.password}
        onChange={handleChangeState}
      />
      <button type="button" onClick={handleClickSignUp}>
        회원가입
      </button>
      <button type="button" onClick={handleClickLogin}>
        로그인
      </button>
      <p>{state.error}</p>
    </div>
  );
}
