import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CardHolderComponentsPage, CardHolderDeleteDialog, CardHolderUpdatePage } from './card-holder.page-object';

const expect = chai.expect;

describe('CardHolder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cardHolderComponentsPage: CardHolderComponentsPage;
  let cardHolderUpdatePage: CardHolderUpdatePage;
  let cardHolderDeleteDialog: CardHolderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CardHolders', async () => {
    await navBarPage.goToEntity('card-holder');
    cardHolderComponentsPage = new CardHolderComponentsPage();
    await browser.wait(ec.visibilityOf(cardHolderComponentsPage.title), 5000);
    expect(await cardHolderComponentsPage.getTitle()).to.eq('jhrApp.cardHolder.home.title');
    await browser.wait(ec.or(ec.visibilityOf(cardHolderComponentsPage.entities), ec.visibilityOf(cardHolderComponentsPage.noResult)), 1000);
  });

  it('should load create CardHolder page', async () => {
    await cardHolderComponentsPage.clickOnCreateButton();
    cardHolderUpdatePage = new CardHolderUpdatePage();
    expect(await cardHolderUpdatePage.getPageTitle()).to.eq('jhrApp.cardHolder.home.createOrEditLabel');
    await cardHolderUpdatePage.cancel();
  });

  it('should create and save CardHolders', async () => {
    const nbButtonsBeforeCreate = await cardHolderComponentsPage.countDeleteButtons();

    await cardHolderComponentsPage.clickOnCreateButton();

    await promise.all([
      cardHolderUpdatePage.setCodeInput('code'),
      cardHolderUpdatePage.setCardSNInput('cardSN'),
      cardHolderUpdatePage.cardTypeSelectLastOption(),
      cardHolderUpdatePage.setTitleInput('title'),
      cardHolderUpdatePage.setThumbnailInput('thumbnail'),
      cardHolderUpdatePage.setDetailsInput('details'),
      cardHolderUpdatePage.setValidPeriodBeginAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardHolderUpdatePage.setValidPeriodEndAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardHolderUpdatePage.setAcceptCategoriesInput('acceptCategories'),
      cardHolderUpdatePage.setAcceptShopsInput('acceptShops'),
      cardHolderUpdatePage.setLeastCostInput('5'),
      cardHolderUpdatePage.setReduceCostInput('5'),
      cardHolderUpdatePage.setDiscountInput('5'),
      cardHolderUpdatePage.setGiftInput('gift'),
      cardHolderUpdatePage.setGiftQuantityInput('5'),
      cardHolderUpdatePage.setReceivedByInput('receivedBy'),
      cardHolderUpdatePage.setReceivedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardHolderUpdatePage.setHeldInput('held')
    ]);

    expect(await cardHolderUpdatePage.getCodeInput()).to.eq('code', 'Expected Code value to be equals to code');
    expect(await cardHolderUpdatePage.getCardSNInput()).to.eq('cardSN', 'Expected CardSN value to be equals to cardSN');
    expect(await cardHolderUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await cardHolderUpdatePage.getThumbnailInput()).to.eq('thumbnail', 'Expected Thumbnail value to be equals to thumbnail');
    expect(await cardHolderUpdatePage.getDetailsInput()).to.eq('details', 'Expected Details value to be equals to details');
    expect(await cardHolderUpdatePage.getValidPeriodBeginAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodBeginAt value to be equals to 2000-12-31'
    );
    expect(await cardHolderUpdatePage.getValidPeriodEndAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodEndAt value to be equals to 2000-12-31'
    );
    const selectedCanUseWithOtherCard = cardHolderUpdatePage.getCanUseWithOtherCardInput();
    if (await selectedCanUseWithOtherCard.isSelected()) {
      await cardHolderUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await cardHolderUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard not to be selected').to.be
        .false;
    } else {
      await cardHolderUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await cardHolderUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard to be selected').to.be
        .true;
    }
    expect(await cardHolderUpdatePage.getAcceptCategoriesInput()).to.eq(
      'acceptCategories',
      'Expected AcceptCategories value to be equals to acceptCategories'
    );
    expect(await cardHolderUpdatePage.getAcceptShopsInput()).to.eq('acceptShops', 'Expected AcceptShops value to be equals to acceptShops');
    expect(await cardHolderUpdatePage.getLeastCostInput()).to.eq('5', 'Expected leastCost value to be equals to 5');
    expect(await cardHolderUpdatePage.getReduceCostInput()).to.eq('5', 'Expected reduceCost value to be equals to 5');
    expect(await cardHolderUpdatePage.getDiscountInput()).to.eq('5', 'Expected discount value to be equals to 5');
    expect(await cardHolderUpdatePage.getGiftInput()).to.eq('gift', 'Expected Gift value to be equals to gift');
    expect(await cardHolderUpdatePage.getGiftQuantityInput()).to.eq('5', 'Expected giftQuantity value to be equals to 5');
    expect(await cardHolderUpdatePage.getReceivedByInput()).to.eq('receivedBy', 'Expected ReceivedBy value to be equals to receivedBy');
    expect(await cardHolderUpdatePage.getReceivedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected receivedDate value to be equals to 2000-12-31'
    );
    expect(await cardHolderUpdatePage.getHeldInput()).to.eq('held', 'Expected Held value to be equals to held');

    await cardHolderUpdatePage.save();
    expect(await cardHolderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cardHolderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CardHolder', async () => {
    const nbButtonsBeforeDelete = await cardHolderComponentsPage.countDeleteButtons();
    await cardHolderComponentsPage.clickOnLastDeleteButton();

    cardHolderDeleteDialog = new CardHolderDeleteDialog();
    expect(await cardHolderDeleteDialog.getDialogTitle()).to.eq('jhrApp.cardHolder.delete.question');
    await cardHolderDeleteDialog.clickOnConfirmButton();

    expect(await cardHolderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
