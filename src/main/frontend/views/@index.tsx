import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/globe-solid.svg' },
  title: 'Home Page',
};

export default function HomepageView() {
  const name = useSignal('');

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <h1>Welcome to the Anagramr App</h1>
      </section>
      <section className="flex p-m gap-m items-end">
        <h5>Please select a function from the panel on the left of the screen.</h5>
      </section>
    </>
  );
}
