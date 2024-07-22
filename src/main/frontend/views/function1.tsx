import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button } from '@vaadin/react-components/Button.js';
import { TextField } from '@vaadin/react-components/TextField.js';
import { AnagramService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/dice-one-solid.svg' },
  title: 'Function 1',
};

export default function Function1View() {
  const input1 = useSignal('');
  const input2 = useSignal('');
  const result = useSignal<boolean | undefined>(undefined);

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Input 1"
          onValueChanged={(e) => {
            input1.value = e.detail.value;
          }}
        />
        <TextField
          label="Input 2"
          onValueChanged={(e) => {
            input2.value = e.detail.value;
          }}
        />
        <Button
          onClick={async () => {
            const serverResponse = await AnagramService.isAnagram(input1.value, input2.value);
            result.value = serverResponse;
          }}
        >
          Submit
        </Button>
      </section>
      {result.value !== undefined && (
        <section style={{ backgroundColor: result.value ? 'green' : 'red' }} className="flex p-m gap-m items-end">
          <h5>{result.value ? 'The words are anagrams!' : 'The words are not anagrams.'}</h5>
        </section>
      )}
    </>
  );
}
