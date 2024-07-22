import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button } from '@vaadin/react-components/Button.js';
import { Notification } from '@vaadin/react-components/Notification.js';
import { TextField } from '@vaadin/react-components/TextField.js';
import Anagram from 'Frontend/generated/com/alexic0n/anagramr/model/Anagram';
import { AnagramService } from 'Frontend/generated/endpoints.js';
import './function2.css';
import { useEffect } from 'react';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/question-circle-solid.svg' },
  title: 'Util',
};

export default function UtilView() {
  const anagrams = useSignal<Anagram[]>([]);

  const retrieveAnagrams = async () => {
    AnagramService.getAllAnagrams().then((serverResponse) => {
      anagrams.value = serverResponse;
    });
  }


  useEffect(() => {
    retrieveAnagrams();
  }, []);

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button
          onClick={async () => {
            await AnagramService.deleteAllAnagrams().then(() => {
              Notification.show('All anagrams have been deleted.');
              retrieveAnagrams();
            })
          }}
        >
          Clear All Anagrams
        </Button>
        <Button
          onClick={() => {
            retrieveAnagrams();
            Notification.show('Anagram table has been refreshed.');
          }}
        >
          Refresh Anagram Table
        </Button>
      </section>
      {anagrams.value.length > 0 ? (
        <section className="flex p-m gap-m items-end">
          <table className="anagram-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Input</th>
                <th>Ordered Input</th>
                <th>Created Date Time</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {anagrams.value.map((anagram, index) => (
                <tr key={index}>
                  <td>{anagram.id}</td>
                  <td>{anagram.input}</td>
                  <td>{anagram.orderedInput}</td>
                  <td>{anagram.createdDateTime}</td>
                  {anagram.id !== undefined && (
                    <td>
                      <Button
                        onClick={async () => {
                          if (anagram.id !== undefined) {
                            await AnagramService.deleteAnagramById(anagram.id).then(() => {
                              Notification.show('Deleted anagram.');
                              retrieveAnagrams();
                            })
                          }
                        }}
                      >
                        Delete Anagram
                      </Button>
                    </td>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      ) :
        (
          <section className="flex p-m gap-m items-end">
            <h5>No anagrams found.</h5>
          </section>
        )}
    </>
  );
}
