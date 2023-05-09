import { useState } from 'react';

import { useNavigate } from 'react-router-dom';

import useSignUp from '../hooks/useSignUp';

import InputField from '../components/InputField';

const initState = {
  id: '',
  password: '',
};

export default function SignUpPage() {
  const [state, setState] = useState(initState);

  const navigate = useNavigate();

  const { signUp } = useSignUp();

  const handleChangeState = ({ key, value }) => {
    setState({
      ...state,
      [key]: value,
    });
  };

  const handleClickCancel = () => {
    navigate('/login');
  };

  const handleClickSignUp = async () => {
    if (!state.id || !state.password) {
      return;
    }
    await signUp({ username: state.id, password: state.password });

    setState(initState);

    navigate('/login');
  };

  return (
    <div>
      <h2>메가테라 쇼핑몰 회원가입</h2>
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
      <button type="button" onClick={handleClickCancel}>
        취소
      </button>
      <button type="button" onClick={handleClickSignUp}>
        가입하기
      </button>
    </div>
  );
}
