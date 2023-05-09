import React from 'react';
import ReactDOM from 'react-dom/client';

import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import routes from './routes';

const router = createBrowserRouter(routes);

function main() {
  const container = document.getElementById('app');
  if (!container) {
    return;
  }

  const root = ReactDOM.createRoot(container);
  root.render((
    <RouterProvider router={router} />
  ));
}

main();
