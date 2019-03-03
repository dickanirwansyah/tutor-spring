package com.dicka.jpaentitymanager.service;

import com.dicka.jpaentitymanager.entity.Produk;
import com.dicka.jpaentitymanager.exception.ResourceNotfound;
import com.dicka.jpaentitymanager.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdukServiceImpl {

    @Autowired
    private ProdukService produkService;

    public List<Produk> getProducts(){
        List<Produk> produks = new ArrayList<>();
        for (Produk produk : produkService.findAll()){
            produks.add(produk);
        }
        return produks;
    }

    public List<Produk> getProductsByJumlah(int jumlah){
        List<Produk> produks = new ArrayList<>();
        for (Produk produk : produkService.findProdukByJumlah(jumlah)){
            produks.add(produk);
        }
        return produks;
    }


    public ResponseData saveAllProduk(List<Produk> produks){
        List<Produk> data = produkService.saveAll(produks);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMessage("success");
        responseData.setData(data);

        return responseData;
    }

    public Produk createProduk(Produk produk){
        Produk p = new Produk();
        p.setHarga(produk.getHarga());
        p.setJumlah(produk.getJumlah());
        p.setNama(produk.getNama());
        //p.setProdukId(UUID.randomUUID().toString());

        return produkService.save(p);
    }

    public Produk updateProduk(Produk produk, Long produkId){
       return produkService.findById(produkId)
                .map(currentProduk -> {
                    currentProduk.setNama(produk.getNama());
                    currentProduk.setJumlah(produk.getJumlah());
                    currentProduk.setHarga(produk.getHarga());
                    return produkService.save(currentProduk);
                }).orElseThrow(() ->
                new ResourceNotfound("produk id : "+produkId+" notfound !"));
    }

    public void deleteProduk(Long produkId){
       Produk produk = produkService.findProdukByProdukId(produkId);
       if (produk == null)
           throw new ResourceNotfound("data produk : "+produk+" not found");

       produkService.delete(produk);
    }
}
