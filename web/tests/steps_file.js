const { I } = inject();

module.exports = () => actor({
  login(id) {
    I.amOnPage('/');

    I.click('로그인 하러가기');

    I.waitForText('메가테라 쇼핑몰 로그인');

    I.click('회원가입');

    I.waitForText('메가테라 쇼핑몰 회원가입');

    I.fillField('아이디', `customer-${id}`);
    I.fillField('비밀번호', 'password');

    I.click('가입하기');

    I.waitForText('메가테라 쇼핑몰 로그인');

    I.fillField('아이디', `customer-${id}`);
    I.fillField('비밀번호', 'password');

    I.wait(2);

    I.click('로그인');
  },
  setupProducts(id) {
    I.amOnPage('/admin');

    I.waitForText('메가테라 쇼핑몰 로그인');

    I.fillField('아이디', 'admin');
    I.fillField('비밀번호', 'admin');

    I.click('로그인');

    I.waitForText('메가테라 쇼핑몰 관리자');

    I.fillField('제품 이름', `데브노트-${id}`);
    I.fillField('가격', '50000');

    I.click('제품 등록하기');

    I.waitForText('데브노트');

    I.fillField('제품 이름', `메가테라 굿즈-${id}`);
    I.fillField('가격', '100000');

    I.click('제품 등록하기');

    I.waitForText('메가테라 굿즈');

    I.click('로그아웃');
  },
});
