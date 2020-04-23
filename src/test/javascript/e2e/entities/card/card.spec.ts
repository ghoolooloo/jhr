import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CardComponentsPage, CardDeleteDialog, CardUpdatePage } from './card.page-object';

const expect = chai.expect;

describe('Card e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cardComponentsPage: CardComponentsPage;
  let cardUpdatePage: CardUpdatePage;
  let cardDeleteDialog: CardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Cards', async () => {
    await navBarPage.goToEntity('card');
    cardComponentsPage = new CardComponentsPage();
    await browser.wait(ec.visibilityOf(cardComponentsPage.title), 5000);
    expect(await cardComponentsPage.getTitle()).to.eq('jhrApp.card.home.title');
    await browser.wait(ec.or(ec.visibilityOf(cardComponentsPage.entities), ec.visibilityOf(cardComponentsPage.noResult)), 1000);
  });

  it('should load create Card page', async () => {
    await cardComponentsPage.clickOnCreateButton();
    cardUpdatePage = new CardUpdatePage();
    expect(await cardUpdatePage.getPageTitle()).to.eq('jhrApp.card.home.createOrEditLabel');
    await cardUpdatePage.cancel();
  });

  it('should create and save Cards', async () => {
    const nbButtonsBeforeCreate = await cardComponentsPage.countDeleteButtons();

    await cardComponentsPage.clickOnCreateButton();

    await promise.all([
      cardUpdatePage.setSnInput('sn'),
      cardUpdatePage.cardTypeSelectLastOption(),
      cardUpdatePage.setTitleInput('title'),
      cardUpdatePage.setThumbnailInput('thumbnail'),
      cardUpdatePage.setDetailsInput('details'),
      cardUpdatePage.setValidPeriodBeginAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardUpdatePage.setValidPeriodEndAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardUpdatePage.setQuantityInput('5'),
      cardUpdatePage.setReceivedQuantityInput('5'),
      cardUpdatePage.setAcceptCategoriesInput('acceptCategories'),
      cardUpdatePage.setAcceptShopsInput('acceptShops'),
      cardUpdatePage.setLeastCostInput('5'),
      cardUpdatePage.setReduceCostInput('5'),
      cardUpdatePage.setDiscountInput('5'),
      cardUpdatePage.setGiftInput('gift'),
      cardUpdatePage.setGiftQuantityInput('5'),
      cardUpdatePage.statusSelectLastOption(),
      cardUpdatePage.setCreatedByInput('createdBy'),
      cardUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardUpdatePage.setDeliveriedByInput('deliveriedBy'),
      cardUpdatePage.setDeliveriedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cardUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await cardUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await cardUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await cardUpdatePage.getThumbnailInput()).to.eq('thumbnail', 'Expected Thumbnail value to be equals to thumbnail');
    expect(await cardUpdatePage.getDetailsInput()).to.eq('details', 'Expected Details value to be equals to details');
    expect(await cardUpdatePage.getValidPeriodBeginAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodBeginAt value to be equals to 2000-12-31'
    );
    expect(await cardUpdatePage.getValidPeriodEndAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodEndAt value to be equals to 2000-12-31'
    );
    expect(await cardUpdatePage.getQuantityInput()).to.eq('5', 'Expected quantity value to be equals to 5');
    expect(await cardUpdatePage.getReceivedQuantityInput()).to.eq('5', 'Expected receivedQuantity value to be equals to 5');
    const selectedCanUseWithOtherCard = cardUpdatePage.getCanUseWithOtherCardInput();
    if (await selectedCanUseWithOtherCard.isSelected()) {
      await cardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await cardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard not to be selected').to.be
        .false;
    } else {
      await cardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await cardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard to be selected').to.be.true;
    }
    expect(await cardUpdatePage.getAcceptCategoriesInput()).to.eq(
      'acceptCategories',
      'Expected AcceptCategories value to be equals to acceptCategories'
    );
    expect(await cardUpdatePage.getAcceptShopsInput()).to.eq('acceptShops', 'Expected AcceptShops value to be equals to acceptShops');
    expect(await cardUpdatePage.getLeastCostInput()).to.eq('5', 'Expected leastCost value to be equals to 5');
    expect(await cardUpdatePage.getReduceCostInput()).to.eq('5', 'Expected reduceCost value to be equals to 5');
    expect(await cardUpdatePage.getDiscountInput()).to.eq('5', 'Expected discount value to be equals to 5');
    expect(await cardUpdatePage.getGiftInput()).to.eq('gift', 'Expected Gift value to be equals to gift');
    expect(await cardUpdatePage.getGiftQuantityInput()).to.eq('5', 'Expected giftQuantity value to be equals to 5');
    expect(await cardUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await cardUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await cardUpdatePage.getDeliveriedByInput()).to.eq('deliveriedBy', 'Expected DeliveriedBy value to be equals to deliveriedBy');
    expect(await cardUpdatePage.getDeliveriedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected deliveriedDate value to be equals to 2000-12-31'
    );
    expect(await cardUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await cardUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await cardUpdatePage.save();
    expect(await cardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Card', async () => {
    const nbButtonsBeforeDelete = await cardComponentsPage.countDeleteButtons();
    await cardComponentsPage.clickOnLastDeleteButton();

    cardDeleteDialog = new CardDeleteDialog();
    expect(await cardDeleteDialog.getDialogTitle()).to.eq('jhrApp.card.delete.question');
    await cardDeleteDialog.clickOnConfirmButton();

    expect(await cardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
