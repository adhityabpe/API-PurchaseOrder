package com.purchaseorder.service;

import com.purchaseorder.model.PoH;
import com.purchaseorder.model.PoD;
import com.purchaseorder.repository.PoHRepository;
import com.purchaseorder.repository.ItemRepository;
import com.purchaseorder.repository.PoDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoService {

    @Autowired
    private PoHRepository poHRepository;

    @Autowired
    private PoDRepository poDRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<PoH> findAllHeaders() {
        return poHRepository.findAll();
    }

    public PoH findHeaderById(int id) {
        return poHRepository.findById(id);
    }

    public PoH saveHeader(PoH poH) {
        return poHRepository.save(poH);
    }

    public PoH updateHeader(int id, PoH poH) {
        if (!poHRepository.existsById(id)) {
            return null;
        }
        poH.setId(id);
        return poHRepository.update(poH);
    }

    public void deleteHeader(int id) {
        if (poHRepository.existsById(id)) {
            poHRepository.deleteById(id);
        }
    }


    public List<PoD> findAllDetails() {
        return poDRepository.findAll();
    }

    public PoD findDetailById(int id) {
        return poDRepository.findById(id);
    }

    public PoD saveDetail(PoD poD) {
        if (!poHRepository.existsById(poD.getPohId())) {
            return null;
        }else if(itemRepository.findById(poD.getItemId())==null){
            return null;
        }
        return poDRepository.save(poD);
    }

    public PoD updateDetail(int id, PoD poD) {
        if (!poDRepository.existsById(id)) {
            return null;
        }
        poD.setId(id);
        return poDRepository.update(poD);
    }

    public void deleteDetail(int id) {
        if (poDRepository.existsById(id)) {
            poDRepository.deleteById(id);
        }
    }
}
