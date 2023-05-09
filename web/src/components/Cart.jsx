import styled from 'styled-components';

const Container = styled.div`
  ul {
    list-style: none;
  }

  ul li {
    padding: 1rem;
  }
  
  ul li button {
    cursor: pointer;
  }
`;

const QuantityController = styled.div`
  display: flex;
`;

const Counter = styled.div`
  display: flex;
  align-items: center;
  margin-inline: 1rem;
  
  span {
    padding-inline: .5rem;
  }
`;

const Remover = styled.div`
  display: flex;
  align-items: center;
`;

function calculateTotalPrice(cartItem) {
  if (!cartItem) {
    return 0;
  }

  return cartItem.reduce((acc, { totalPrice }) => acc + totalPrice, 0);
}

export default function Cart({ items, onChangeQuantity }) {
  return (
    <Container>
      <h2>장바구니</h2>
      {!items?.length ? (
        <p>상품을 선택해 주세요!</p>
      ) : (
        <ul>
          {items.map(({
            id, productName, quantity, totalPrice, unitPrice,
          }) => (
            <li key={id}>
              <QuantityController>
                <p>
                  {productName}
                  (
                  {unitPrice.toLocaleString()}
                  원)
                </p>
                <Counter>
                  <button
                    type="button"
                    onClick={() => onChangeQuantity(id, quantity - 1)}
                  >
                    -
                  </button>
                  <span>
                    수량:
                    {' '}
                    {quantity}
                    개
                  </span>
                  <button
                    type="button"
                    onClick={() => onChangeQuantity(id, quantity + 1)}
                  >
                    +
                  </button>
                </Counter>
                <Remover>
                  <button
                    type="button"
                    onClick={() => onChangeQuantity(id, 0)}
                  >
                    X
                  </button>
                </Remover>
              </QuantityController>
              <p>
                &gt; 금액:
                {' '}
                {totalPrice.toLocaleString()}
                원
              </p>
            </li>
          ))}
        </ul>
      )}
      <div>
        총 금액:
        {' '}
        {calculateTotalPrice(items).toLocaleString()}
        원
      </div>
    </Container>
  );
}
