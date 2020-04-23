import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WriteOffCardComponentsPage, WriteOffCardDeleteDialog, WriteOffCardUpdatePage } from './write-off-card.page-object';

const expect = chai.expect;

describe('WriteOffCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let writeOffCardComponentsPage: WriteOffCardComponentsPage;
  let writeOffCardUpdatePage: WriteOffCardUpdatePage;
  let writeOffCardDeleteDialog: WriteOffCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load WriteOffCards', async () => {
    await navBarPage.goToEntity('write-off-card');
    writeOffCardComponentsPage = new WriteOffCardComponentsPage();
    await browser.wait(ec.visibilityOf(writeOffCardComponentsPage.title), 5000);
    expect(await writeOffCardComponentsPage.getTitle()).to.eq('jhrApp.writeOffCard.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(writeOffCardComponentsPage.entities), ec.visibilityOf(writeOffCardComponentsPage.noResult)),
      1000
    );
  });

  it('should load create WriteOffCard page', async () => {
    await writeOffCardComponentsPage.clickOnCreateButton();
    writeOffCardUpdatePage = new WriteOffCardUpdatePage();
    expect(await writeOffCardUpdatePage.getPageTitle()).to.eq('jhrApp.writeOffCard.home.createOrEditLabel');
    await writeOffCardUpdatePage.cancel();
  });

  it('should create and save WriteOffCards', async () => {
    const nbButtonsBeforeCreate = await writeOffCardComponentsPage.countDeleteButtons();

    await writeOffCardComponentsPage.clickOnCreateButton();

    await promise.all([
      writeOffCardUpdatePage.setCodeInput('code'),
      writeOffCardUpdatePage.setCardSNInput('cardSN'),
      writeOffCardUpdatePage.cardTypeSelectLastOption(),
      writeOffCardUpdatePage.setTitleInput('title'),
      writeOffCardUpdatePage.setThumbnailInput('thumbnail'),
      writeOffCardUpdatePage.setDetailsInput('details'),
      writeOffCardUpdatePage.setValidPeriodBeginAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      writeOffCardUpdatePage.setValidPeriodEndAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      writeOffCardUpdatePage.setAcceptCategoriesInput('acceptCategories'),
      writeOffCardUpdatePage.setAcceptShopsInput('acceptShops'),
      writeOffCardUpdatePage.setLeastCostInput('5'),
      writeOffCardUpdatePage.setReduceCostInput('5'),
      writeOffCardUpdatePage.setDiscountInput('5'),
      writeOffCardUpdatePage.setGiftInput('gift'),
      writeOffCardUpdatePage.setGiftQuantityInput('5'),
      writeOffCardUpdatePage.setReceivedByInput('receivedBy'),
      writeOffCardUpdatePage.setReceivedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      writeOffCardUpdatePage.setOrderSNInput('orderSN'),
      writeOffCardUpdatePage.statusSelectLastOption(),
      writeOffCardUpdatePage.setWriteOffDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await writeOffCardUpdatePage.getCodeInput()).to.eq('code', 'Expected Code value to be equals to code');
    expect(await writeOffCardUpdatePage.getCardSNInput()).to.eq('cardSN', 'Expected CardSN value to be equals to cardSN');
    expect(await writeOffCardUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await writeOffCardUpdatePage.getThumbnailInput()).to.eq('thumbnail', 'Expected Thumbnail value to be equals to thumbnail');
    expect(await writeOffCardUpdatePage.getDetailsInput()).to.eq('details', 'Expected Details value to be equals to details');
    expect(await writeOffCardUpdatePage.getValidPeriodBeginAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodBeginAt value to be equals to 2000-12-31'
    );
    expect(await writeOffCardUpdatePage.getValidPeriodEndAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodEndAt value to be equals to 2000-12-31'
    );
    const selectedCanUseWithOtherCard = writeOffCardUpdatePage.getCanUseWithOtherCardInput();
    if (await selectedCanUseWithOtherCard.isSelected()) {
      await writeOffCardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await writeOffCardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard not to be selected').to
        .be.false;
    } else {
      await writeOffCardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await writeOffCardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard to be selected').to.be
        .true;
    }
    expect(await writeOffCardUpdatePage.getAcceptCategoriesInput()).to.eq(
      'acceptCategories',
      'Expected AcceptCategories value to be equals to acceptCategories'
    );
    expect(await writeOffCardUpdatePage.getAcceptShopsInput()).to.eq(
      'acceptShops',
      'Expected AcceptShops value to be equals to acceptShops'
    );
    expect(await writeOffCardUpdatePage.getLeastCostInput()).to.eq('5', 'Expected leastCost value to be equals to 5');
    expect(await writeOffCardUpdatePage.getReduceCostInput()).to.eq('5', 'Expected reduceCost value to be equals to 5');
    expect(await writeOffCardUpdatePage.getDiscountInput()).to.eq('5', 'Expected discount value to be equals to 5');
    expect(await writeOffCardUpdatePage.getGiftInput()).to.eq('gift', 'Expected Gift value to be equals to gift');
    expect(await writeOffCardUpdatePage.getGiftQuantityInput()).to.eq('5', 'Expected giftQuantity value to be equals to 5');
    expect(await writeOffCardUpdatePage.getReceivedByInput()).to.eq('receivedBy', 'Expected ReceivedBy value to be equals to receivedBy');
    expect(await writeOffCardUpdatePage.getReceivedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected receivedDate value to be equals to 2000-12-31'
    );
    expect(await writeOffCardUpdatePage.getOrderSNInput()).to.eq('orderSN', 'Expected OrderSN value to be equals to orderSN');
    expect(await writeOffCardUpdatePage.getWriteOffDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected writeOffDate value to be equals to 2000-12-31'
    );

    await writeOffCardUpdatePage.save();
    expect(await writeOffCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await writeOffCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last WriteOffCard', async () => {
    const nbButtonsBeforeDelete = await writeOffCardComponentsPage.countDeleteButtons();
    await writeOffCardComponentsPage.clickOnLastDeleteButton();

    writeOffCardDeleteDialog = new WriteOffCardDeleteDialog();
    expect(await writeOffCardDeleteDialog.getDialogTitle()).to.eq('jhrApp.writeOffCard.delete.question');
    await writeOffCardDeleteDialog.clickOnConfirmButton();

    expect(await writeOffCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
