import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FinalCardComponentsPage, FinalCardDeleteDialog, FinalCardUpdatePage } from './final-card.page-object';

const expect = chai.expect;

describe('FinalCard e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let finalCardComponentsPage: FinalCardComponentsPage;
  let finalCardUpdatePage: FinalCardUpdatePage;
  let finalCardDeleteDialog: FinalCardDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load FinalCards', async () => {
    await navBarPage.goToEntity('final-card');
    finalCardComponentsPage = new FinalCardComponentsPage();
    await browser.wait(ec.visibilityOf(finalCardComponentsPage.title), 5000);
    expect(await finalCardComponentsPage.getTitle()).to.eq('jhrApp.finalCard.home.title');
    await browser.wait(ec.or(ec.visibilityOf(finalCardComponentsPage.entities), ec.visibilityOf(finalCardComponentsPage.noResult)), 1000);
  });

  it('should load create FinalCard page', async () => {
    await finalCardComponentsPage.clickOnCreateButton();
    finalCardUpdatePage = new FinalCardUpdatePage();
    expect(await finalCardUpdatePage.getPageTitle()).to.eq('jhrApp.finalCard.home.createOrEditLabel');
    await finalCardUpdatePage.cancel();
  });

  it('should create and save FinalCards', async () => {
    const nbButtonsBeforeCreate = await finalCardComponentsPage.countDeleteButtons();

    await finalCardComponentsPage.clickOnCreateButton();

    await promise.all([
      finalCardUpdatePage.setSnInput('sn'),
      finalCardUpdatePage.cardTypeSelectLastOption(),
      finalCardUpdatePage.setTitleInput('title'),
      finalCardUpdatePage.setThumbnailInput('thumbnail'),
      finalCardUpdatePage.setDetailsInput('details'),
      finalCardUpdatePage.setValidPeriodBeginAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setValidPeriodEndAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setQuantityInput('5'),
      finalCardUpdatePage.setReceivedQuantityInput('5'),
      finalCardUpdatePage.setAcceptCategoriesInput('acceptCategories'),
      finalCardUpdatePage.setAcceptShopsInput('acceptShops'),
      finalCardUpdatePage.setLeastCostInput('5'),
      finalCardUpdatePage.setReduceCostInput('5'),
      finalCardUpdatePage.setDiscountInput('5'),
      finalCardUpdatePage.setGiftInput('gift'),
      finalCardUpdatePage.setGiftQuantityInput('5'),
      finalCardUpdatePage.statusSelectLastOption(),
      finalCardUpdatePage.setCreatedByInput('createdBy'),
      finalCardUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setDeliveriedByInput('deliveriedBy'),
      finalCardUpdatePage.setDeliveriedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setDiscontinueByInput('discontinueBy'),
      finalCardUpdatePage.setDiscontinueDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      finalCardUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await finalCardUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await finalCardUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await finalCardUpdatePage.getThumbnailInput()).to.eq('thumbnail', 'Expected Thumbnail value to be equals to thumbnail');
    expect(await finalCardUpdatePage.getDetailsInput()).to.eq('details', 'Expected Details value to be equals to details');
    expect(await finalCardUpdatePage.getValidPeriodBeginAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodBeginAt value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getValidPeriodEndAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected validPeriodEndAt value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getQuantityInput()).to.eq('5', 'Expected quantity value to be equals to 5');
    expect(await finalCardUpdatePage.getReceivedQuantityInput()).to.eq('5', 'Expected receivedQuantity value to be equals to 5');
    const selectedCanUseWithOtherCard = finalCardUpdatePage.getCanUseWithOtherCardInput();
    if (await selectedCanUseWithOtherCard.isSelected()) {
      await finalCardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await finalCardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard not to be selected').to.be
        .false;
    } else {
      await finalCardUpdatePage.getCanUseWithOtherCardInput().click();
      expect(await finalCardUpdatePage.getCanUseWithOtherCardInput().isSelected(), 'Expected canUseWithOtherCard to be selected').to.be
        .true;
    }
    expect(await finalCardUpdatePage.getAcceptCategoriesInput()).to.eq(
      'acceptCategories',
      'Expected AcceptCategories value to be equals to acceptCategories'
    );
    expect(await finalCardUpdatePage.getAcceptShopsInput()).to.eq('acceptShops', 'Expected AcceptShops value to be equals to acceptShops');
    expect(await finalCardUpdatePage.getLeastCostInput()).to.eq('5', 'Expected leastCost value to be equals to 5');
    expect(await finalCardUpdatePage.getReduceCostInput()).to.eq('5', 'Expected reduceCost value to be equals to 5');
    expect(await finalCardUpdatePage.getDiscountInput()).to.eq('5', 'Expected discount value to be equals to 5');
    expect(await finalCardUpdatePage.getGiftInput()).to.eq('gift', 'Expected Gift value to be equals to gift');
    expect(await finalCardUpdatePage.getGiftQuantityInput()).to.eq('5', 'Expected giftQuantity value to be equals to 5');
    expect(await finalCardUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await finalCardUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getDeliveriedByInput()).to.eq(
      'deliveriedBy',
      'Expected DeliveriedBy value to be equals to deliveriedBy'
    );
    expect(await finalCardUpdatePage.getDeliveriedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected deliveriedDate value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getDiscontinueByInput()).to.eq(
      'discontinueBy',
      'Expected DiscontinueBy value to be equals to discontinueBy'
    );
    expect(await finalCardUpdatePage.getDiscontinueDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected discontinueDate value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await finalCardUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await finalCardUpdatePage.save();
    expect(await finalCardUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await finalCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last FinalCard', async () => {
    const nbButtonsBeforeDelete = await finalCardComponentsPage.countDeleteButtons();
    await finalCardComponentsPage.clickOnLastDeleteButton();

    finalCardDeleteDialog = new FinalCardDeleteDialog();
    expect(await finalCardDeleteDialog.getDialogTitle()).to.eq('jhrApp.finalCard.delete.question');
    await finalCardDeleteDialog.clickOnConfirmButton();

    expect(await finalCardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
