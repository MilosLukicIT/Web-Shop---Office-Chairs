// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUserUri: 'http://localhost:8080/user',
  apiArticle: 'http://localhost:8080/article',
  apiCustomerOrderArticle: 'http://localhost:8080/customerOrderArticle',
  apiCustomerOrder: 'http://localhost:8080/customerOrder',
  apiUserLogin: 'http://localhost:8080/auth/login',
  apiArticleBrand: 'http://localhost:8080/articleBrand',
  apiArticleType: 'http://localhost:8080/articleType',
  apiPayment: 'http://localhost:8080/payment',
  stripeKey: 'pk_test_51PIa4RJgn0NMoXTZsh6hjw2AecPikJ90ebqqRrXHXskgnqqF93iiJMWymNUg00t7lHxLJXD91qsSNyXZ0exjkVbC00EF5v0aoJ',

  fireBaseConfig: {
    apiKey: "AIzaSyDAlsay3Yno6QukQy_jBkM1u33bRXNJTsM",
  authDomain: "officechairimg.firebaseapp.com",
  projectId: "officechairimg",
  storageBucket: "officechairimg.appspot.com",
  messagingSenderId: "223156226088",
  appId: "1:223156226088:web:9529929f82f034d1918d1d"
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
