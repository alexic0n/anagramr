import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button } from '@vaadin/react-components/Button.js';
import { Notification } from '@vaadin/react-components/Notification.js';
import { TextField } from '@vaadin/react-components/TextField.js';
import Anagram from 'Frontend/generated/com/alexic0n/anagramr/model/Anagram';
import { AnagramService } from 'Frontend/generated/endpoints.js';
import './function2.css';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/dice-two-solid.svg' },
  title: 'Function 2',
};

export default function Function2View() {
  const input = useSignal('');
  const result = useSignal<Anagram[]>([]);

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Input"
          onValueChanged={(e) => {
            input.value = e.detail.value;
          }}
        />
        <Button
          onClick={async () => {
            const serverResponse = await AnagramService.getAllAnagramsOfInput(input.value);
            if (serverResponse.length === 0) {
              Notification.show('No anagrams found for the given input.');
            }
            console.log(serverResponse);
            result.value = serverResponse;
          }}
        >
          Retrieve anagrams
        </Button>
      </section>
      {result.value.length > 0 && (
        <section className="flex p-m gap-m items-end">
          <table className="anagram-table">
            <thead>
              <tr>
                <th>Input</th>
                <th>Ordered Input</th>
                <th>Created Date Time</th>
              </tr>
            </thead>
            <tbody>
              {result.value.map((anagram, index) => (
                <tr key={index}>
                  <td>{anagram.input}</td>
                  <td>{anagram.orderedInput}</td>
                  <td>{anagram.createdDateTime}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      )}
    </>
  );
}
