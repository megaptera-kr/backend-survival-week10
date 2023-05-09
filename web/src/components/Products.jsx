import styled from 'styled-components';

const Container = styled.div`
  ul {
    list-style: none;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  }

  ul li {
    padding: 1rem;
  }

  ul li button {
    width: 100%;
    cursor: pointer;
  }
`;

export default function Products({ products, onClick }) {
  return (
    <Container>
      <h2>제품목록</h2>
      {!products?.length ? (
        <p>등록된 제품이 없습니다!</p>
      ) : (
        <ul>
          {products.map(({ id, name, price }) => (
            <li key={id}>
              <button type="button" onClick={() => onClick(id)}>
                <p>
                  {name}
                </p>
                <p>
                  (
                  {price.toLocaleString()}
                  원)
                </p>
              </button>
            </li>
          ))}
        </ul>
      )}
    </Container>
  );
}
