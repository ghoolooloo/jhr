import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CarouselComponentsPage, CarouselDeleteDialog, CarouselUpdatePage } from './carousel.page-object';

const expect = chai.expect;

describe('Carousel e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let carouselComponentsPage: CarouselComponentsPage;
  let carouselUpdatePage: CarouselUpdatePage;
  let carouselDeleteDialog: CarouselDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Carousels', async () => {
    await navBarPage.goToEntity('carousel');
    carouselComponentsPage = new CarouselComponentsPage();
    await browser.wait(ec.visibilityOf(carouselComponentsPage.title), 5000);
    expect(await carouselComponentsPage.getTitle()).to.eq('jhrApp.carousel.home.title');
    await browser.wait(ec.or(ec.visibilityOf(carouselComponentsPage.entities), ec.visibilityOf(carouselComponentsPage.noResult)), 1000);
  });

  it('should load create Carousel page', async () => {
    await carouselComponentsPage.clickOnCreateButton();
    carouselUpdatePage = new CarouselUpdatePage();
    expect(await carouselUpdatePage.getPageTitle()).to.eq('jhrApp.carousel.home.createOrEditLabel');
    await carouselUpdatePage.cancel();
  });

  it('should create and save Carousels', async () => {
    const nbButtonsBeforeCreate = await carouselComponentsPage.countDeleteButtons();

    await carouselComponentsPage.clickOnCreateButton();

    await promise.all([
      carouselUpdatePage.setPictureInput('picture'),
      carouselUpdatePage.setSortInput('5'),
      carouselUpdatePage.statusSelectLastOption(),
      carouselUpdatePage.setCreatedByInput('createdBy'),
      carouselUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      carouselUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      carouselUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await carouselUpdatePage.getPictureInput()).to.eq('picture', 'Expected Picture value to be equals to picture');
    expect(await carouselUpdatePage.getSortInput()).to.eq('5', 'Expected sort value to be equals to 5');
    expect(await carouselUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await carouselUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await carouselUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await carouselUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await carouselUpdatePage.save();
    expect(await carouselUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await carouselComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Carousel', async () => {
    const nbButtonsBeforeDelete = await carouselComponentsPage.countDeleteButtons();
    await carouselComponentsPage.clickOnLastDeleteButton();

    carouselDeleteDialog = new CarouselDeleteDialog();
    expect(await carouselDeleteDialog.getDialogTitle()).to.eq('jhrApp.carousel.delete.question');
    await carouselDeleteDialog.clickOnConfirmButton();

    expect(await carouselComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
