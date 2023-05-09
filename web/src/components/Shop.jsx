import useFetchProducts from '../hooks/useFetchProducts';
import useFetchCart from '../hooks/useFetchCart';
import useAddCartItem from '../hooks/useAddCartItem';

import Products from './Products';
import Cart from './Cart';

export default function Shop({ loggedIn, onClickLogin, onClickLogout }) {
  const { products } = useFetchProducts();

  const { lineItems, reload } = useFetchCart();

  const { addCartItem, changeQuantity } = useAddCartItem();

  const handleClickProduct = async (productId) => {
    if (loggedIn) {
      await addCartItem({ productId });

      reload();
    }
  };

  const handleChangeQuantity = async (id, quantity) => {
    await changeQuantity({ id, quantity });

    reload();
  };

  return (
    <>
      <h2>메가테라 쇼핑몰</h2>
      {loggedIn ? (
        <button type="button" onClick={onClickLogout}>
          로그아웃
        </button>
      ) : null}
      <Products products={products} onClick={handleClickProduct} />
      {loggedIn ? (
        <Cart items={lineItems} onChangeQuantity={handleChangeQuantity} />
      ) : (
        <>
          <hr />
          <p>로그인을 하셔야 이용이 가능합니다.</p>
          <button type="button" onClick={onClickLogin}>
            로그인 하러가기
          </button>
        </>
      )}
    </>
  );
}
