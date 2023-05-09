import styled from 'styled-components';

const Container = styled.div`
  padding-block: .5rem;
`;

export default function InputField({
  type = 'text', label, name, value, onChange,
}) {
  const id = `input-${name}`;

  const handleChange = (event) => {
    const { target } = event;

    onChange({
      key: target.name,
      value: target.value,
    });
  };

  return (
    <Container>
      <label htmlFor={id}>{label}</label>
      <input
        type={type}
        id={id}
        name={name}
        value={value}
        onChange={handleChange}
      />
    </Container>
  );
}
