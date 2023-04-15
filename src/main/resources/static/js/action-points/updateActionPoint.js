let role = document.querySelector('#role').dataset.role || null;
const state = {}


    //     TODO: finish implementation
    (function () {
        if (!role || role !== 'YOUTH_COUNCIL_ADMINISTRATOR') {
            console.log("ur are nothing")
            return;
        }

        const proposals = [...document.querySelectorAll('.proposal')];
        proposals.forEach(proposal => {
            state[proposal.dataset.proposalId] = {
                proposal,
                proposalDetails: proposal.querySelector('.proposal-details'),
                proposalStatus: proposal.querySelector('.proposal-status'),
            }
        })
    })();